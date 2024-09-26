import java.util.*;
import java.io.*;

class UnpackingAct {
    public static void main(String[] arg) throws Exception {
        System.out.println("------------------------------------------------");
        System.out.println("---------- Marvellous Packer Unpacker ----------");
        System.out.println("------------------------------------------------");

        Scanner sobj = new Scanner(System.in);

        System.out.println("Enter the name of packed file that you want to unpack:");
        String packedFileName = sobj.nextLine();

        File packedFile = new File(packedFileName);
        if (!packedFile.exists()) {
            System.out.println("Packed file does not exist.");
            sobj.close();
            return;
        }

        try (FileInputStream fis = new FileInputStream(packedFile)) {
            byte[] header = new byte[100];
            int bytesRead, fileCount = 0;

            while ((bytesRead = fis.read(header)) != -1) {
                // Read the header and extract file name and size
                String headerStr = new String(header).trim();
                String[] tokens = headerStr.split(" ");
                String fileName = tokens[0];
                int fileSize = Integer.parseInt(tokens[1]);

                // Create new file and write the unpacked data
                File newFile = new File(fileName);
                if (newFile.createNewFile()) {
                    byte[] fileData = new byte[fileSize];
                    fis.read(fileData);

                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        fos.write(fileData);
                    }

                    System.out.println(fileName + " unpacked successfully.");
                    fileCount++;
                }
            }

            System.out.println("------------------------------------------------");
            System.out.println("Unpacking activity completed.");
            System.out.println("Total files unpacked successfully: " + fileCount);
            System.out.println("------------------------------------------------");
        }

        sobj.close();
    }
}
