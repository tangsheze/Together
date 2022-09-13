package com.dg.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ty
 * @apiNote
 */

public class IdTool {
    private static final Logger log = LoggerFactory.getLogger(IdTool.class);
    private static final String S_INT = "0123456789";
    private static final String S_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String S_ALL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static Sequence WORKER = new Sequence();
    public static final DateTimeFormatter MILLISECOND = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public IdTool() {
    }

    public static long getId() {
        return WORKER.nextId();
    }

    public static String getIdStr() {
        return String.valueOf(WORKER.nextId());
    }

    public static String getMillisecond() {
        return LocalDateTime.now().format(MILLISECOND);
    }

    public static String getTimeId() {
        return getMillisecond() + getId();
    }

    public static void initSequence(long workerId, long datacenterId) {
        WORKER = new Sequence(workerId, datacenterId);
    }

    public static String get32UUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return (new UUID(random.nextLong(), random.nextLong())).toString().replace("-", "");
    }

    public static String randomId(int count) {
        return randomId(count, IdTool.RandomType.ALL);
    }

    public static String randomId(int count, IdTool.RandomType randomType) {
        if (count == 0) {
            return "";
        } else {
            Assert.isTrue(count > 0, "Requested random string length " + count + " is less than 0.");
            ThreadLocalRandom random = ThreadLocalRandom.current();
            char[] buffer = new char[count];

            for(int i = 0; i < count; ++i) {
                if (IdTool.RandomType.INT == randomType) {
                    buffer[i] = "0123456789".charAt(random.nextInt("0123456789".length()));
                } else if (IdTool.RandomType.STRING == randomType) {
                    buffer[i] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".length()));
                } else {
                    buffer[i] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(random.nextInt("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".length()));
                }
            }

            return new String(buffer);
        }
    }

    public static enum RandomType {
        INT,
        STRING,
        ALL;

        private RandomType() {
        }
    }
}
