#include <dirent.h>
#include <sstream>
#include <string>
#include <vector>

#include "linux_parser.h"
#include "parser_consts.h"
#include "parser_helper.h"

using std::stof;
using std::string;
using std::to_string;
using std::vector;

// An example of how to read data from the filesystem
string LinuxParser::OperatingSystem() {
  string line;
  string key;
  string value;
  std::ifstream filestream(ParserConsts::kOSPath);
  if (filestream.is_open()) {
    while (std::getline(filestream, line)) {
      std::replace(line.begin(), line.end(), ' ', '_');
      std::replace(line.begin(), line.end(), '=', ' ');
      std::replace(line.begin(), line.end(), '"', ' ');
      std::istringstream linestream(line);
      while (linestream >> key >> value) {
        if (key == "PRETTY_NAME") {
          std::replace(value.begin(), value.end(), '_', ' ');
          return value;
        }
      }
    }
  }
  return value;
}

// An example of how to read data from the filesystem
string LinuxParser::Kernel() {
  string os, version, kernel;
  string line;
  std::ifstream stream(ParserConsts::kProcDirectory +
                       ParserConsts::kVersionFilename);
  if (stream.is_open()) {
    std::getline(stream, line);
    std::istringstream linestream(line);
    linestream >> os >> version >> kernel;
  }
  return kernel;
}

// Update this to use std::filesystem
vector<int> LinuxParser::Pids() {
  vector<int> pids;
  DIR* directory = opendir(ParserConsts::kProcDirectory.c_str());
  struct dirent* file;
  while ((file = readdir(directory)) != nullptr) {
    // Is this a directory?
    if (file->d_type == DT_DIR) {
      // Is every character of the name a digit?
      string filename(file->d_name);
      if (std::all_of(filename.begin(), filename.end(), isdigit)) {
        int pid = stoi(filename);
        pids.push_back(pid);
      }
    }
  }
  closedir(directory);
  return pids;
}


// Read and return the system memory utilization
float LinuxParser::MemoryUtilization() {
  float memTotal = ParserHelper::GetValueByKey<int>(
      ParserConsts::filterMemTotalString, ParserConsts::kMeminfoFilename);
  float memFree = ParserHelper::GetValueByKey<int>(
      ParserConsts::filterMemFreeString, ParserConsts::kMeminfoFilename);

  float memory = (memTotal - memFree) / memTotal;

  return memory;
}


// Read and return the system uptime
long LinuxParser::UpTime() {
  string line;
  long upTime = ParserHelper::GetValue<long>(ParserConsts::kUptimeFilename);
  return upTime;
}


// Read and return the total number of processes
int LinuxParser::TotalProcesses() {
  return ParserHelper::GetValueByKey<int>(ParserConsts::filterProcesses,
                                          ParserConsts::kStatFilename);
}


// Read and return the number of running processes
int LinuxParser::RunningProcesses() {
  return ParserHelper::GetValueByKey<int>(ParserConsts::filterRunningProcesses,
                                          ParserConsts::kStatFilename);
}


// Read and return the user associated with a process
string LinuxParser::UserByUID(int UID) {
  string line, user, x;
  int fileUid;

  std::ifstream filestream(ParserConsts::kPasswordPath);
  if (filestream.is_open()) {
    while (std::getline(filestream, line)) {
      std::replace(line.begin(), line.end(), ':', ' ');
      std::istringstream linestream(line);
      while (linestream >> user >> x >> fileUid) {
        if (fileUid == UID) {
          return user;
        }
      }
    }
  }
  return user;
}