#pragma once

#include <exception>
#include <string>

class RingCirclesException:public std::exception{
  private:
    std::string message;  // エラーメッセージ

  public:
    RingCirclesException(const std::string& msg):message(msg){};
    virtual ~RingCirclesException() throw(){};
    void printStackTrace() const{
      std::cerr << "Error in generating Circle:" << message << std::endl;
    }
};
