package com.example.application.exception.file;

import com.example.application.exception.BaseException;

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
