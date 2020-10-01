#ifndef SYSTEM_PARSER_H
#define SYSTEM_PARSER_H

#include <fstream>
#include <regex>
#include <string>

#include "parser_helper.h"

using std::string;
using std::vector;

namespace LinuxParser {
// System
float MemoryUtilization();
long UpTime();
vector<int> Pids();
int TotalProcesses();
int RunningProcesses();
string OperatingSystem();
string Kernel();
string UserByUID(int);

std::vector<string> CpuUtilization();
};  // namespace LinuxParser

#endif