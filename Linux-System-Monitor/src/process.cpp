#include <unistd.h>
#include <cctype>
#include <iostream>
#include <iterator>
#include <sstream>
#include <string>
#include <vector>

#include "format.h"
#include "linux_parser.h"
#include "parser_consts.h"
#include "parser_helper.h"
#include "process.h"

using namespace std;

Process::Process(int pid, long Hertz) : pid_(pid), Hertz_(Hertz) {
  vector<string> cpuNumbers = ReadFile(pid);

  utime_ = stof(cpuNumbers[13]);
  stime_ = stof(cpuNumbers[14]);
  cutime_ = stof(cpuNumbers[15]);
  cstime_ = stof(cpuNumbers[16]);
  starttime_ = stof(cpuNumbers[21]);
}


// Return this process's ID
int Process::Pid() { return pid_; }


// Return this process's CPU utilization
double Process::CpuUtilization() {
  long uptime = LinuxParser::UpTime();
  double total_time = utime_ + stime_ + cutime_ + cstime_;

  double seconds = uptime - (starttime_ / Hertz_);
  double cpu_usage = (total_time / Hertz_) / seconds;

  return cpu_usage;
}


// Return the command that generated this process
string Process::Command() {
  string cmd = ParserHelper::GetValue<string>(to_string(pid_) +
                                              ParserConsts::kCmdlineFilename);
  size_t maxSize = 50;
  if(cmd.size() > maxSize) {
    cmd.resize(maxSize - 3);
    cmd = cmd + "...";
  }                                            
  return cmd;
}

float Process::RawRam() {
  float memInKB = ParserHelper::GetValueByKey<float>(
      ParserConsts::filterProcMem,
      to_string(pid_) + ParserConsts::kStatusFilename);
  return memInKB;
}


// Return this process's memory utilization
string Process::Ram() {
  float memInKB = RawRam();
  return Format::KBisMB(memInKB);
}


// Return the user (name) that generated this process
string Process::User() {
  int UID = ParserHelper::GetValueByKey<int>(
      ParserConsts::filterUID, to_string(pid_) + ParserConsts::kStatusFilename);

  string user = LinuxParser::UserByUID(UID);
  return user;
}


// Return the age of this process (in seconds)
long int Process::UpTime() {
  long uptime = LinuxParser::UpTime();
  long seconds = uptime - (starttime_ / Hertz_);

  return seconds;
}

vector<string> Process::ReadFile(int pid) {
  string line, skip;

  std::ifstream stream(ParserConsts::kProcDirectory + to_string(pid) +
                       ParserConsts::kStatFilename);

  getline(stream, line);
  istringstream linestream(line);
  istream_iterator<string> beg(linestream), end;
  vector<string> cpuNumbers(beg, end);
  return cpuNumbers;
};