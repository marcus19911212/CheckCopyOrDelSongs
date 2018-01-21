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
 * ģ��������ƺ�ɾ�����̣�������D:\\CopyMusic�ļ����д���һЩ����(��Ϊmp3��ʽ)
	ѭ����ʾ�ò˵�����ѡ����Ҫ���еĲ�����1����ѯ���и���   2�����ݸ������Ƹ���  3�����ݸ�������ɾ�� 4: �˳�
	������
		�û����룺1
			�������¸�����
				������ɯ������
				�̻�����
				�Ϻ�̲
				Сƻ��
				ҹ������������

		�û����룺2
			������Ҫ���Ƶĸ�������: �Ϻ�̲
			������洢·��: D:\\songs  (˵��:��·����������򲻴���,�������򴴽�)
			���ƽ��: �����Ϻ�̲�Ѿ��ɹ����Ƶ�D:\\songsĿ¼��

		�û����룺3
			������Ҫɾ���ĸ�������: �Ϻ�̲
			ɾ�����: �����Ϻ�̲�Ѿ��ɹ�ɾ��

		�û�����: 4
			�˳�ϵͳ
 */
public class CheckCopyOrDelSongs {
	public static void main(String[] args) throws IOException {
		// ���ļ��з�װ���ļ��ж���
		File src = new File("D:\\CopyMusic");
		// ��������¼�����
		Scanner sc = new Scanner(System.in);
		// ѭ����ʾ�ò˵�
		while (true) {

			System.out.println("��ѡ����Ҫ���еĲ����� ");
			System.out.println("  1����ѯ���и���");
			System.out.println("  2�����ݸ������Ƹ���");
			System.out.println("  3�����ݸ�������ɾ�� ");
			System.out.println("  4: �˳�");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				// ��ѯ���и���
				System.out.println("�������¸�����");
				checkAllSongs(src);
				break;
			case 2:
				// ���ݸ������Ƹ���
				copySongs(src);
				break;
			case 3:
				// ���ݸ�������ɾ��
				delSongs(src);
				break;
			case 4:
				// �˳�
			default:
				System.out.println("�˳�ϵͳ�ɹ�");
				System.exit(0);
				break;
			}
		}
	}

	// 1����ѯ���и���
	public static void checkAllSongs(File src) {
		// ��ȡ��ǰ�ļ����µ��ļ������ļ�
		File[] files = src.listFiles();
		for (File file : files) {// �����ļ�����
			if (file.isFile()) {
				if (file.getName().endsWith(".mp3")) {
					System.out.println(file.getName());
				}

			} else if (file.isDirectory()) {
				checkAllSongs(file);
			}
		}
	}

	// ���ݸ������Ƹ���
	/*
	 * ������Ҫ���Ƶĸ�������: �Ϻ�̲ ������洢·��: E:\\songs (˵��:��·����������򲻴���,�������򴴽�) ���ƽ��:
	 * �����Ϻ�̲�Ѿ��ɹ����Ƶ�E:\\songsĿ¼��
	 */
	public static void copySongs(File src) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("������Ҫ���Ƶĸ�������");
		String songName = sc.nextLine();
		// ����src�ļ���
		File[] files = src.listFiles();
		boolean flag = false;
		for (File file : files) {

			int index = file.getName().lastIndexOf(".");
			String str = file.getName().substring(0, index);
			if (str.equals(songName)) {
				System.out.println("������洢·��: ");
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
				System.out.println(str + "���Ƴɹ�");
			} else {
				flag = true;
			}
		}
		if (flag) {
			System.out.println("��Ҫ���Ƶĸ���������");
		}
	}

	/*
	 * ������Ҫɾ���ĸ�������: �Ϻ�̲ ɾ�����: �����Ϻ�̲�Ѿ��ɹ�ɾ��
	 */
	public static void delSongs(File src) {
		Scanner sc = new Scanner(System.in);
		System.out.println("������Ҫɾ���ĸ�������:");
		String songName = sc.nextLine();
		// ����src�ļ���
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
			System.out.println(file.getName() + "ɾ���ɹ�");
		} else {
			System.out.println("��Ҫɾ���ĸ���������");
		}
	}

}
