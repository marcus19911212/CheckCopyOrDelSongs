package com.companyname.homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import com.sun.org.apache.xalan.internal.utils.FeatureManager.Feature;

/*
 * 模拟歌曲复制和删除过程：假设在D:\\CopyMusic文件夹中存在一些歌曲(均为mp3格式)
	循环显示该菜单：请选择您要进行的操作：1：查询所有歌曲   2：根据歌曲名称复制  3：根据歌曲名称删除 4: 退出
	举例：
		用户输入：1
			存在以下歌曲：
				蒙娜丽莎的眼泪
				烟花易冷
				上海滩
				小苹果
				夜空中最亮的星

		用户输入：2
			请输入要复制的歌曲名称: 上海滩
			请输入存储路径: D:\\songs  (说明:该路径如果存在则不创建,不存在则创建)
			复制结果: 歌曲上海滩已经成功复制到D:\\songs目录中

		用户输入：3
			请输入要删除的歌曲名称: 上海滩
			删除结果: 歌曲上海滩已经成功删除

		用户输入: 4
			退出系统
 */
public class CheckCopyOrDelSongs {
	public static void main(String[] args) throws IOException {
		// 把文件夹封装成文件夹对象
		File src = new File("D:\\CopyMusic");
		// 创建键盘录入对象
		Scanner sc = new Scanner(System.in);
		// 循环显示该菜单
		while (true) {

			System.out.println("请选择您要进行的操作： ");
			System.out.println("  1：查询所有歌曲");
			System.out.println("  2：根据歌曲名称复制");
			System.out.println("  3：根据歌曲名称删除 ");
			System.out.println("  4: 退出");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				// 查询所有歌曲
				System.out.println("存在以下歌曲：");
				checkAllSongs(src);
				break;
			case 2:
				// 根据歌曲名称复制
				copySongs(src);
				break;
			case 3:
				// 根据歌曲名称删除
				delSongs(src);
				break;
			case 4:
				// 退出
			default:
				System.out.println("退出系统成功");
				System.exit(0);
				break;
			}
		}
	}

	// 1：查询所有歌曲
	public static void checkAllSongs(File src) {
		// 获取当前文件夹下的文件或子文件
		File[] files = src.listFiles();
		for (File file : files) {// 遍历文件对象
			if (file.isFile()) {
				if (file.getName().endsWith(".mp3")) {
					System.out.println(file.getName());
				}

			} else if (file.isDirectory()) {
				checkAllSongs(file);
			}
		}
	}

	// 根据歌曲名称复制
	/*
	 * 请输入要复制的歌曲名称: 上海滩 请输入存储路径: E:\\songs (说明:该路径如果存在则不创建,不存在则创建) 复制结果:
	 * 歌曲上海滩已经成功复制到E:\\songs目录中
	 */
	public static void copySongs(File src) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入要复制的歌曲名称");
		String songName = sc.nextLine();
		// 遍历src文件夹
		File[] files = src.listFiles();
		boolean flag = false;
		for (File file : files) {

			int index = file.getName().lastIndexOf(".");
			String str = file.getName().substring(0, index);
			if (str.equals(songName)) {
				System.out.println("请输入存储路径: ");
				String destPath = sc.nextLine();

				FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(new File(destPath, file.getName()));
				byte[] b = new byte[1024 * 8];
				int len;
				while ((len = fis.read(b)) != -1) {
					fos.write(b, 0, len);
				}
				fis.close();
				fos.close();
				System.out.println(str + "复制成功");
			} else {
				flag = true;
			}
		}
		if (flag) {
			System.out.println("您要复制的歌曲不存在");
		}
	}

	/*
	 * 请输入要删除的歌曲名称: 上海滩 删除结果: 歌曲上海滩已经成功删除
	 */
	public static void delSongs(File src) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入要删除的歌曲名称:");
		String songName = sc.nextLine();
		// 遍历src文件夹
		File[] files = src.listFiles();
		int index = -1;
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().equals(songName + ".mp3")) {
				index = i;
			}

		}
		//
		if (index != -1) {
			File file = files[index];
			file.delete();
			System.out.println(file.getName() + "删除成功");
		} else {
			System.out.println("您要删除的歌曲不存在");
		}
	}

}
