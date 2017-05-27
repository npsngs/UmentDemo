#ifndef UMENGLIB_H
#define UMENGLIB_H
#include <stdlib.h>
#include <string>
class BeanCoder;

class UMEnvelope
{
    public:
        char *version = "V1.0";
        char *address = ;
        char *signature;
        unsigned int serial_num;
        unsigned int ts_secs;
        unsigned int length;
        unsigned char *entity;

        char *guid;
        char *checksum;
        unsigned int codex;


        void packTo(BeanCoder beanCode);
        void unpackFrom(BeanCoder beanCode);
}

class BeanCoder
{
    public:

}


#endif

