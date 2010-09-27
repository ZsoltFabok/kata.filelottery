package com.zsoltfabok.kata;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class FileLotteryTest {

    private FakeRandom random;
    private FileLottery fileLottery;

    @Before
    public void setUp() {
        random = new FakeRandom();
    }

    @Test
    public void shouldReturnTheOnlyFileFromTheDirectory() {
        File parent = new FakeFile(new File[]{new File("1")});
        fileLottery = new FileLottery(parent, random);

        random.setNextInt(0);
        assertEquals("1", fileLottery.nextRandomPath());
        random.assertNextIntArgument(1);
    }

    @Test
    public void shouldReturTheFilenameIfTheDirectoryIsActuallyAFile() {
        File parent = new FakeFile("1");
        fileLottery = new FileLottery(parent, random);

        assertEquals("1", fileLottery.nextRandomPath());
    }

    @Test
    public void shouldReturnEmptyStringIfTheDirectoryIsEmpty() {
        File parent = new FakeFile(new File[]{});
        fileLottery = new FileLottery(parent, random);

        assertEquals("", fileLottery.nextRandomPath());
    }

    @Test
    public void shouldReturnARandomFileFromTheDirectory() {
        File parent = new FakeFile(new File[]{new File("1"), new File("2")});
        fileLottery = new FileLottery(parent, random);

        random.setNextInt(1);
        assertEquals("2", fileLottery.nextRandomPath());
        random.assertNextIntArgument(2);
    }

    @Test
    public void shouldReturnTheLeftoverFileFromTheDirectory() {
        File parent = new FakeFile(new File[]{new File("1"), new File("2")});
        fileLottery = new FileLottery(parent, random);

        random.setNextInt(1);
        fileLottery.nextRandomPath();

        random.setNextInt(0);
        assertEquals("1", fileLottery.nextRandomPath());
        random.assertNextIntArgument(1);
    }

    @Test
    public void shouldTurnOverWhenNoMoreFileLeftToReturn() {
        File parent = new FakeFile(new File[]{new File("1"),
                new File("2"), new File("3")});
        fileLottery = new FileLottery(parent, random);

        random.setNextInt(0);
        fileLottery.nextRandomPath();
        random.setNextInt(1);
        fileLottery.nextRandomPath();
        random.setNextInt(0);
        fileLottery.nextRandomPath();

        random.setNextInt(2);
        assertEquals("3", fileLottery.nextRandomPath());
        random.assertNextIntArgument(3);
    }
}
