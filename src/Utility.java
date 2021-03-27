public class Utility {

    public static double[][] extractSubArray(double[][] array, int startIndexRow, int endIndexRow, int startIndexCol, int endIndexCol)
    {
        double[][] extractedSubArray = new double[(endIndexRow-startIndexRow)+1][(endIndexCol-startIndexCol)+1];
        int i,j;
        int m,n;
        for(i=startIndexRow , m=0 ; i<=endIndexRow; i++,m++)
        {
            for(j=startIndexCol, n=0 ; j<=endIndexCol; j++,n++)
            {
                extractedSubArray[m][n] = array[i][j];
            }
        }

        return extractedSubArray;
    }
}
