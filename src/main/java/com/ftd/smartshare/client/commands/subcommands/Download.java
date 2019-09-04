package com.ftd.smartshare.client.commands.subcommands;

import picocli.CommandLine;

@CommandLine.Command(
        description = "Downloads a file",
        name = "download",
        aliases = "d",
        mixinStandardHelpOptions = true
)
public class Download implements Runnable {

    @CommandLine.Parameters(arity="1", index = "0", description = "Name of file to be downloaded")
    private String fileName;

    @CommandLine.Parameters(arity="1", index = "1", description = "The password for the file")
    private String password;

    public void run() {
        System.out.println("Downloading " + fileName);
    }

}
