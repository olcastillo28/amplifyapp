// This file was modified using Laptop
// This file was modified in VS Code

package backend;

public class wellXplorer {

    public static void main(String args[])
    {
        utilities utilidades = new utilities();
        trajectory tr, tr2 = new trajectory();
        tr = utilidades.surveyListText("data/BH0198.txt");
        tr.minimumCurvature();
        tr2 = tr.interFile("data/interDepths.txt");
        tr2.imprimirSurveys();
    }
}