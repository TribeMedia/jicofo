/*
 * Jicofo, the Jitsi Conference Focus.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package org.jitsi.protocol;

import net.java.sip.communicator.util.Logger;

import org.jitsi.util.*;

import java.util.*;

/**
 * Class encapsulates configuration properties for Jitsi Meet conference that
 * are attached to create conference request
 * {@link org.jitsi.jicofo.xmpp.ConferenceIq}. Options are configured in
 * 'config.js' file of Jitsi Meet Java Script application.
 *
 * @author Pawel Domas
 */
public class JitsiMeetConfig
{
    /**
     * The logger.
     */
    private final static Logger logger
        = Logger.getLogger(JitsiMeetConfig.class);

    /**
     * The name of channel last N configuration property. Should be non-negative
     * number. Pass <tt>-1</tt> to disable last N functionality.
     */
    public static final String CHANNEL_LAST_N_PNAME = "channelLastN";

    /**
     * The name of adaptive last N configuration property. Pass 'true' to enable
     * or 'false' to disable.
     */
    public static final String ADAPTIVE_LAST_N_PNAME = "adaptiveLastN";

    /**
     * The name of adaptive simulcast configuration property. Pass 'true' to
     * enable or 'false' to disable.
     */
    public static final String ADAPTIVE_SIMULCAST_PNAME = "adaptiveSimulcast";

    private final Map<String, String> properties;

    /**
     * Creates new <tt>JitsiMeetConfig</tt> from given properties map.
     * @param properties a string to string map that contains name to value
     *                   mapping of configuration properties.
     */
    public JitsiMeetConfig(Map<String, String> properties)
    {
        this.properties = properties;
    }

    /**
     * Returns an integer value of channel last N property or <tt>null</tt>
     * if it has not been specified.
     */
    public Integer getChannelLastN()
    {
        return getInt(CHANNEL_LAST_N_PNAME);
    }

    /**
     * Returns a boolean value of adaptive last N property or <tt>null</tt>
     * if it has not been specified.
     */
    public Boolean isAdaptiveLastNEnabled()
    {
        return getBoolean(ADAPTIVE_LAST_N_PNAME);
    }

    /**
     * Returns a boolean value of adaptive simulcast property or <tt>null</tt>
     * if it has not been specified.
     */
    public Boolean isAdaptiveSimulcastEnabled()
    {
        return getBoolean(ADAPTIVE_SIMULCAST_PNAME);
    }

    private Boolean getBoolean(String name)
    {
        String stringValue = properties.get(name);
        Boolean boolValue = null;

        if (!StringUtils.isNullOrEmpty(stringValue))
        {
            //try
            //{
            boolValue = Boolean.parseBoolean(stringValue);
            //}
            //catch (NumberFormatException ex)
            //{
            //    logger.error(
            //        "Error parsing: " + name + ", v: " + stringValue, ex);
            //}
        }
        return boolValue;
    }

    private Integer getInt(String name)
    {
        String stringValue = properties.get(name);
        Integer intValue = null;

        if (!StringUtils.isNullOrEmpty(stringValue))
        {
            try
            {
                intValue = Integer.parseInt(stringValue);
            }
            catch (NumberFormatException ex)
            {
                logger.error(
                    "Error parsing: " + name + ", v: " + stringValue, ex);
            }
        }
        return intValue;
    }
}