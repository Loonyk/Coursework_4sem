package com.example.a4sem_rpp;

public class Sleep extends Thread {
    MainActivity mainActivity;

    Sleep (MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void sleeping() throws Exception{   // trows - защита от исключений. Объявляет о таком
        // поведении, чтобы методы смогли себя защитить
        Thread.sleep(2000);
        mainActivity.openMenuActivity();

    }

    @Override
    public void run(){     //переопределение метода run из Runnable, запуск потока
        super.run();
        try {           // обработка возможных исключений
            sleeping();
        } catch (Exception x){
            return;
        }
    }
}
