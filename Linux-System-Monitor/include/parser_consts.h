#ifndef CONSTS_PARSER_H
#define CONSTS_PARSER_H

#include <string>

using std::string;

namespace ParserConsts {
const string kProcDirectory{"/proc/"};
const string kCmdlineFilename{"/cmdline"};
const string kCpuinfoFilename{"/cpuinfo"};
const string kStatusFilename{"/status"};
const string kStatFilename{"/stat"};
const string kUptimeFilename{"/uptime"};
const string kMeminfoFilename{"/meminfo"};
const string kVersionFilename{"/version"};
const string kOSPath{"/etc/os-release"};
const string kPasswordPath{"/etc/passwd"};

const string filterProcesses("processes");
const string filterRunningProcesses("procs_running");
const string filterMemTotalString("MemTotal:");
const string filterMemFreeString("MemFree:");
const string filterCpu("cpu");
const string filterUID("Uid:");
const string filterProcMem("VmData:");

}  // namespace ParserConsts

#endif