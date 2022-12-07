package com.example.springboot.exception.file;

import com.example.springboot.exception.BaseException;

/**
 * 文件信息异常类
 *
 * @author eaf
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
