#ifndef FORMAT_H
#define FORMAT_H

#include <string>

namespace Format {
std::string Format(int);
std::string ElapsedTime(long times);
std::string KBisMB(float kb);
};  // namespace Format

#endif