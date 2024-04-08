package com.lab5;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ICommand {

    public abstract void execute() throws MalformedURLException, IOException;
}