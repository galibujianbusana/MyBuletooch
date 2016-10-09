package myapplication.com.myapplication;

import android.os.Environment;
import android.os.StatFs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SDcardHelper {

	/**
	*
	*
	* 判断sdcard是否挂载，可用
	* */
	public static boolean isSDcardMounted() {
		boolean state = Environment.getExternalStorageState().
				equals(Environment.MEDIA_MOUNTED);		
		return state;
	}
	
	/**
	 * 获取sdcard的路径
	 *
	 */
	public static String getSDCardPath() {
		String path = Environment.getExternalStorageDirectory().getAbsolutePath();
		return path;
	}
	
	/**
	 * 获取sdcard的大小
	 *
	 */
	public static long getSDCardSize() {
		if (isSDcardMounted()) {
			//
			StatFs sf = new StatFs(getSDCardPath());
			long count = sf.getBlockCount();
			long size = sf.getBlockSize();
			return count * size;
		}
		return 0;
	}
	
	/**
	 * 获取sdcard的空余空间大小
	 */
	
	public static long getSDCardFreeSize() {
		if (isSDcardMounted()) {
			StatFs sFs = new StatFs(getSDCardPath());
			long count = sFs.getFreeBlocks();
			long size = sFs.getBlockSize();
			return count * size;
		}
		return 0;
	}
	
	/**
	 * 获取sdcard的可利用空间大小
	 */
	
	public static long getSDCardAvailableSize() {
		if (isSDcardMounted()) {
			StatFs sFs = new StatFs(getSDCardPath());
			long count = sFs.getAvailableBlocks();
			long size = sFs.getBlockSize();
			return count * size;
		}
		return 0;
	}
	
	/**
	 * 保存文件到sdcard
	 * @param data  保存目标数组
	 * @param dir  保存路径
	 * @param filename  文件名
	 * @return
	 */
	public static boolean saveFileToSDCard(byte[] data, String dir,String filename) {
		if (isSDcardMounted()) {//�ж�Sdcard�Ƿ����
			File filedir = new File(getSDCardPath() + File.separator + dir);
			if (!filedir.exists()) {//�Ƿ����Ŀ¼���������򴴽�
				filedir.mkdirs();
			}
			
			if (getSDCardAvailableSize() >= data.length) {//�жϿռ��Ƿ���				
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(new File(filedir+File.separator+filename));
					fos.write(data);
					fos.flush();
					return true;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					if (fos != null) {
						try {
							fos.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	
		return false;
	}

	/**
	 * 从sdcard路径读取文件 ，返回字符数组
	 *
	 */

	public static byte[] readFileFromSDCard(String filepath)    {
		if (isSDcardMounted()) {
			File file = new File(filepath);
			ByteArrayOutputStream  byteArrayOutputStream = null;
			if (file.exists()) {
				FileInputStream fileInputStream = null;
				try {
					fileInputStream = new FileInputStream(file);
					byteArrayOutputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int length = 0;
					while ((length = fileInputStream.read(buffer)) != -1) {
						byteArrayOutputStream.write(buffer, 0, length);
					}
					
					return byteArrayOutputStream.toByteArray();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (fileInputStream != null) {
						try {
							fileInputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				
			}
		}
		return null;
	}
}
