package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * ������֤��ͼƬ
 * @author Administrator
 *
 */
public class ImageUtil {
	// ͼƬ�Ŀ�ȡ�
    private int width = 160;
    // ͼƬ�ĸ߶ȡ�
    private int height = 40;
    // ��֤���ַ�����
    private int codeCount = 5;
    // ��֤���������
    private int lineCount = 150;
    // ��֤��
    private String code = null;
 
    private char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
 
    private BufferedImage bufferedImage;
 
    private ImageUtil(int width, int height){
        this.width = width;
        this.height = height;
    }
 
    public ImageUtil(int width, int height, int codeCount, int lineCount){
        this(width, height);
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        createCodeImage();
    }
    private void createCodeImage(){
        //�ַ�����x����
        int x = 0;
        //����߶�
        int fontHeight = 0;
        //�ַ�����y����
        int codeY = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        x = width / (codeCount + 2);
        fontHeight = height - 2;
        codeY = height - 4;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        Random random = new Random();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, width,height);
        Font font = new Font("Fixedays",Font.PLAIN,fontHeight);
        graphics2D.setFont(font);
 
        for (int i = 0; i < lineCount; i++) {
            //x���һ�����λ��
            int x1 = random.nextInt(width);
            //y���һ�����λ��
            int y1 = random.nextInt(height);
            //x��ڶ������λ��
            int x2 = x1 + random.nextInt(width >> 2);
            //y��ڶ������λ��
            int y2 = y1 + random.nextInt(height >> 2);
 
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
 
            graphics2D.setColor(new Color(red, green, blue));
            graphics2D.drawLine(x1, y1, x2, y2);
        }
 
        StringBuffer randomCode = new StringBuffer(codeCount);
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            graphics2D.setColor(new Color(red, green, blue));
            graphics2D.drawString(strRand, (i +1) * x, codeY);
            randomCode.append(strRand);
        }
        code = randomCode.toString();
    }
 
    public void write(String path) throws IOException {
        OutputStream outputStream = new FileOutputStream(path);
        this.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
 
    public void write(OutputStream outputStream) throws IOException {
        ImageIO.write(bufferedImage, "png", outputStream);
    }
 
    public BufferedImage getBufferedImage(){
        return bufferedImage;
    }
 
    public String getCode(){
        return code;
    }
}
