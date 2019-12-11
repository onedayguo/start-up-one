package advance.atguigu;


public class SparseArray {
    public static void main(String[] args){
        //创建一个原始的二维数组11*11
        //0 表示没有妻子，1表示黑子，2表示白子
        int chessArray1[][] = new int[11][11];
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;
        chessArray1[8][6] = 1;
        //输出原始的二维数组
        System.out.println("原始的二维数组：");
        for (int[] row:chessArray1){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组转为稀疏数组
        //1.遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i=0;i<11;i++){
            for (int j =0;j<11;j++){
                if (chessArray1[i][j] !=0){
                    sum++;
                }
            }
        }
        //2 创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存到sparseArr数组中
        int count = 0;  //用于记录是第几个非0的数据
        for (int i = 0;i<11;i++){
            for (int j = 0;j<11;j++){
                if (chessArray1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArray1[i][j];

                }
            }
        }
        //输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为......");
        for (int i = 0; i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

        //将稀疏数组 恢复成 原始的二维数组
        //先读取行和列
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //给原始数组赋值
        for (int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组.....");
        for (int[] row:chessArr2){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
