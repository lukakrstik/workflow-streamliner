import java.io.File;

public class htmlGen {
    public static void main(String[] args) {
        File folder = new File("F:\\Test");
        File[] listOfFiles = folder.listFiles();
        String[] arrayOptimized = new String[50];
        String[] arrayThumb = new String[50];
        int arraycount1 = 0;
        int arraycount2 = 0;

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                if (listOfFiles[i].getName().contains("O")) {
                    arrayOptimized[arraycount1] = listOfFiles[i].getName();
                    arraycount1++;
                } else if (listOfFiles[i].getName().contains("T")) {
                    arrayThumb[arraycount2] = listOfFiles[i].getName();
                    arraycount2++;
                }
            }

        }
        System.out.println("All Files Loaded, Noobs owned and pussies initiated...");
        for (int counter = 0; arrayOptimized[counter] != null && arrayThumb[counter] != null; counter++) {
            System.out.println(
            "{ isColor: , isFeatured: , tlink: 'https://github.com/lukakrstik/website-photo-pool/blob/master/images/" + arrayThumb[counter] + "?raw=true', olink: 'https://github.com/lukakrstik/website-photo-pool/blob/master/images/" + arrayOptimized[counter] + "?raw=true' },");
        }
        System.out.println("All URLs Generated, Pushed to Mainframe. All pussies fucked.");
    }
}
/*
"<div class=\"card m-2\" style=\"border: none;\">\n"+
        "\t<a href=\"" + "https://github.com/lukakrstik/website-photo-pool/blob/master/images/" + arrayOptimized[counter] + "?raw=true\" data-fancybox=\"gallery\">\n"+
        "\t\t<img class=\"card-img\" src= \"" + "https://github.com/lukakrstik/website-photo-pool/blob/master/images/" + arrayThumb[counter] + "?raw=true\">\n"+
        "\t</a>\n"+
        "</div>\n\n"*/
