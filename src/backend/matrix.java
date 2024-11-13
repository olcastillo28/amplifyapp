package backend;
/*
 * Esta clase representa matrices bidimensionales para realizar operaciones de lgebra lineal
 */
public class matrix {

    private int filas;
    private int columnas;
    double elementos[][];

    public void matrix(int nFilas, int nColumnas)
    {
        // Construye el arreglo que va acontener los elementos
        filas = nFilas;
        columnas = nColumnas;
        elementos = new double[filas][columnas];

        //Inicializa en ceros todos los elementos de la matriz
        for(int i = 0; i < filas ; i++)
        {
            for(int j = 0; j < columnas ; j++)
            {
                elementos[i][j] = 0.0;
            }
        }
    }
    /*
     * Establece u nvalor dentro de la matriz
     */
    public void setValue(int x, int y, double value)
    {
        elementos[x][y] = value;
    }
    /*
     * Retorna un valor de la matriz por fila y columna
     */
    public double getValue(int x, int y)
    {
        return elementos[x][y];
    }
    /**
     * Retorna matriz de cofactores para la fila i y columna j
     */
    public double[][] getCofactors(int x, int y)
    {
        return null;
    }
    /**
     * Retorna determinante de la matriz
     */
    public double getDeterminant()
    {
        return 0.0;
    }
}
