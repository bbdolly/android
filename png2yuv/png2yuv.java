package png2yuv;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrix;

public class png2yuv {

	static int target_width=1280;
	static int target_height=720;
	
	public static void main(String[] args) throws Exception {
		if(args.length < 2) 
		{
			System.out.print("usage: png2yuv inputifle outputfile!\n");
		}
		FileInputStream fis = new FileInputStream(args[0]);
		Bitmap bitmap  = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(fis),target_width,target_height,false);
		byte [] yuvdata= new byte[target_width*target_height*3/2];
		Arrays.fill(yuvdata, (byte)0x80);
		int row,col;
		for(col=0;col<target_width/2;col++)
			for(row=0;row<target_height/2;row++)
			{
				int [] yuv11 = convertRGB2YUV(bitmap.getPixel(2*col+0, 2*row+0));
				int [] yuv12 = convertRGB2YUV(bitmap.getPixel(2*col+1, 2*row+0));
				int [] yuv21 = convertRGB2YUV(bitmap.getPixel(2*col+0, 2*row+1));
				int [] yuv22 = convertRGB2YUV(bitmap.getPixel(2*col+1, 2*row+1));
				yuvdata[(2*row+0)*target_width+(2*col+0)] = (byte)yuv11[0];
				yuvdata[(2*row+0)*target_width+(2*col+1)] = (byte)yuv12[0];
				yuvdata[(2*row+1)*target_width+(2*col+0)] = (byte)yuv21[0];
				yuvdata[(2*row+1)*target_width+(2*col+1)] = (byte)yuv22[0];
				
				yuvdata[target_height * target_width + row * target_width + 2*col+0] = (byte) ((yuv11[1] + yuv12[1] + yuv21[1] + yuv22[1]) / 4);
				yuvdata[target_height * target_width + row * target_width + 2*col+1] = (byte) ((yuv11[2] + yuv12[2] + yuv21[2] + yuv22[2]) / 4);
			}
		FileOutputStream out = new FileOutputStream(args[1]);
		out.write(yuvdata);
		out.close();
	}

	static private int[] convertRGB2YUV(int color) 
	{
		ColorMatrix cm = new ColorMatrix();
		cm.setRGB2YUV();
		final float[] yuvArray = cm.getArray();
		
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		int[] result = new int[3];

		// Adding a 127 U and V.
		result[0] = floatToByte(yuvArray[0] * r + yuvArray[1] * g + yuvArray[2] * b);
		result[1] = floatToByte(yuvArray[5] * r + yuvArray[6] * g + yuvArray[7] * b) + 127;
		result[2] = floatToByte(yuvArray[10] * r + yuvArray[11] * g + yuvArray[12] * b) + 127;
		return result;
	}
	
	static private int floatToByte(float x) {
		int n = java.lang.Math.round(x);
		return n;
	}
	
}
