package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        long longNum = 11111111111111L;
        int integerNum = 1;
        short shortNum = 1;
        byte byteNum = 1;
        double doubleNum = 1.1;
        float floatNum = 1.1F;
        char charSym = '1';
        boolean booleanValue = true;
        LOG.debug("long : {}", longNum);
        LOG.debug("integer : {}", integerNum);
        LOG.info("short : {}", shortNum);
        LOG.warn("byte : {}", byteNum);
        LOG.error("double : {}", doubleNum);
        LOG.debug("float : {}", floatNum);
        LOG.debug("char : {}", charSym);
        LOG.info("boolean : {}", booleanValue);
    }
}