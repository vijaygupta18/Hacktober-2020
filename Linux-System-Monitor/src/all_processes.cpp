#include "all_processes.h"
#include <unistd.h>
#include <algorithm>
#include <vector>
#include "linux_parser.h"
#include "process.h"

using std::sort;
using std::vector;

bool compareProcesses(Process& p1, Process& p2) {
  return (p1.RawRam() > p2.RawRam());
};

All_Processes::All_Processes() {
  Hertz = sysconf(_SC_CLK_TCK);
  UpdateProcesses();
}

vector<Process>& All_Processes::GetProcesses() {
  UpdateProcesses();
  return all_processes_;
}

void All_Processes::UpdateProcesses() {
  current_pids_ = ReadFolders();

  bool changed = false;

  AddNewProcesses(changed);
  RemoveFinishedProcesses(changed);

  if (changed) {
    sort(all_processes_.begin(), all_processes_.end(), compareProcesses);
  }
}

vector<int> All_Processes::ReadFolders() { return LinuxParser::Pids(); };

void All_Processes::AddNewProcesses(bool& changed) {
  for (std::size_t i = 0; i < current_pids_.size(); ++i) {
    int current_pid = current_pids_[i];

    if (std::find_if(all_processes_.begin(), all_processes_.end(),
                     [current_pid](Process& n) {
                       return n.Pid() == current_pid;
                     }) == all_processes_.end()) {
      changed = true;
      Process process(current_pids_[i], Hertz);

      all_processes_.emplace_back(process);
    }
  }
}

void All_Processes::RemoveFinishedProcesses(bool& changed) {
  for (size_t i = 0; i < all_processes_.size(); i++) {
    int current_pid = all_processes_[i].Pid();

    if (std::find(current_pids_.begin(), current_pids_.end(), current_pid) ==
        current_pids_.end()) {
      changed = true;

      all_processes_.erase(all_processes_.begin() + i);
    }
  }
}

