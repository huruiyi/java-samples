package vip.fairy.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import lombok.Getter;

@Getter
public class SanYouTask implements Delayed {

    private final String taskContent;

    private final Long triggerTime;

    public SanYouTask(String taskContent, Long delayTime) {
        this.taskContent = taskContent;
        this.triggerTime = System.currentTimeMillis() + delayTime * 1000;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(triggerTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return this.triggerTime.compareTo(((SanYouTask) o).triggerTime);
    }

}
