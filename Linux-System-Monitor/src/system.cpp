#include <string>
#include <vector>

#include "linux_parser.h"
#include "process.h"
#include "processor.h"
#include "all_processes.h"
#include "system.h"
#include "format.h"

using std::string;
using std::vector;

// Return the system's CPU
Processor& System::Cpu() { return cpu_; }

//Return a container composed of the system's processes
All_Processes& System::Processes() { return processes_; }

// Return the system's kernel identifier (string)
string System::Kernel() { return string(LinuxParser::Kernel()); }

// Return the system's memory utilization
float System::MemoryUtilization() { return LinuxParser::MemoryUtilization(); }

// Return the operating system name
string System::OperatingSystem() { return LinuxParser::OperatingSystem(); }

// Return the number of processes actively running on the system
int System::RunningProcesses() { return LinuxParser::RunningProcesses(); }

// Return the total number of processes on the system
int System::TotalProcesses() { return LinuxParser::TotalProcesses(); }

//Return the number of seconds since the system started running
long int System::UpTime() { return LinuxParser::UpTime(); }