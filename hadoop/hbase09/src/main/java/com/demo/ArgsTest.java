package com.demo;

import org.apache.hadoop.ha.BadFencingConfigurationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lg
 *         Date: 6/5/17
 *         Time: 2:39 PM
 */
public class ArgsTest {

    public static void main(String[] args) throws Exception {
        Args args1 = new Args("master:2014");
        System.out.println(args1.sshPort + "=" + args1.user);
    }

    static class Args {
        private static final Pattern USER_PORT_RE = Pattern.compile("([^:]+?)?(?:\\:(\\d+))?");
        private static final int DEFAULT_SSH_PORT = 22;
        String user = System.getProperty("user.name");
        int sshPort = 22;

        public Args(String arg) throws BadFencingConfigurationException {
            if(arg != null) {
                Matcher m = USER_PORT_RE.matcher(arg);
                if(!m.matches()) {
                    throw new BadFencingConfigurationException("Unable to parse user and SSH port: " + arg);
                }

                if(m.group(1) != null) {
                    this.user = m.group(1);
                }

                if(m.group(2) != null) {
                    this.sshPort = this.parseConfiggedPort(m.group(2)).intValue();
                }
            }

        }

        private Integer parseConfiggedPort(String portStr) throws BadFencingConfigurationException {
            try {
                return Integer.valueOf(portStr);
            } catch (NumberFormatException var3) {
                throw new BadFencingConfigurationException("Port number '" + portStr + "' invalid");
            }
        }
    }
}
