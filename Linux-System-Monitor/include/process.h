#ifndef PROCESS_H
#define PROCESS_H

#include <string>
#include <vector>

using std::string;
using std::vector;

/*
Basic class for Process representation
It contains relevant attributes as shown below
*/
class Process {
 private:
  int pid_;
  long Hertz_;
  float utime_ = 0.0;
  float stime_ = 0.0;
  float cutime_ = 0.0;
  float cstime_ = 0.0;
  float starttime_ = 0.0;

  vector<string> ReadFile(int);

 public:
  Process(int, long);
  int Pid();
  string User();
  string Command();
  double CpuUtilization();
  float RawRam();
  string Ram();
  long int UpTime();
};

#endif