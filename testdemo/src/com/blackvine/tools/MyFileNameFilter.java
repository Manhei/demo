package com.blackvine.tools;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 过滤后缀名为png的文件
 * @author songjunfeng
 *
 */
public class MyFileNameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {

		return name.endsWith(".png");
		
	}

}
