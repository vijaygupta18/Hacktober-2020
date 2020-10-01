#include "format.h"
#include <iomanip>
#include <sstream>
#include <string>
#
using std::string;
using std::to_string;

string Format::Format(int time) {
  string timeAsString = to_string(time);
  return string(2 - timeAsString.length(), '0') + timeAsString;
}

// INPUT: Long int measuring seconds
// OUTPUT: HH:MM:SS
// REMOVE: [[maybe_unused]] once you define the function
string Format::ElapsedTime(long seconds) {
  int hour = 0;
  int min = 0;
  int sec = 0;

  hour = seconds / 3600;
  seconds = seconds % 3600;
  min = seconds / 60;
  seconds = seconds % 60;
  sec = seconds;

  return Format(hour) + ':' + Format(min) + ':' + Format(sec);
}

string Format::KBisMB(float kb) {
  float mb = kb / 1024;
  std::stringstream mb_stream;
  mb_stream << std::fixed << std::setprecision(1) << mb;
  return mb_stream.str();
}