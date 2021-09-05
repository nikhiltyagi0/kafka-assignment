package consumers;

class ConsumerListener implements Runnable {


    @Override
    public void run() {
        Consumers.consumers();
    }
}
