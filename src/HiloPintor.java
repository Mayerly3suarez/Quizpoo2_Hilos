public class HiloPintor extends Thread  {

    public void run(){
        int i =0;
        for (;;i++) {

            try {
                Thread.sleep(3000);
                System.out.println("Haciendo repaint "+i + "H:"+HilosGrafica.cantidadHombres +
                        "M:"+ HilosGrafica.cantidadMujeres);
               HilosGrafica.panelGrafica.repaint();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }


    }
}
