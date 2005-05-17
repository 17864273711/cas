/*
 * Copyright 2005 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.validation;

import java.util.List;

import org.jasig.cas.authentication.principal.Service;

/**
 * Return from CentralAuthenticationService.validateServiceTicket(String,
 * Service), the Assertion contains a chain of Principal objects. The first is
 * the User's logon Principal, while any others are Proxy Principals.
 * <p>
 * In normal use, the Assertion is passed to the Web layer where a reference to
 * it is stored in a Map that Spring calls the Model. This is passed to the View
 * layer where a Servlet or JSP formats it into the response to the /validate or
 * /serviceValidate
 * </p>
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public interface Assertion {

    /**
     * Get a List of Principals which represent the owners of the
     * GrantingTickets which granted the ticket that was validated. The first
     * Principal of this list is the Principal which originally authenticated to
     * CAS to obtain the first Granting Ticket. Subsequent Principals are those
     * associated with GrantingTickets that were granted from that original
     * granting ticket. The last Principal in this List is that associated with
     * the GrantingTicket that was the immediate grantor of the ticket that was
     * validated. The List returned by this method will contain at least one
     * Principal.
     * 
     * @return a List of Principals
     */
    List getChainedPrincipals();

    /**
     * True if the validated ticket was granted in the same transaction as that
     * in which its grantor GrantingTicket was originally issued.
     * 
     * @return true if validated ticket was granted simultaneous with its
     * grantor's issuance
     */
    boolean isFromNewLogin();

    /**
     * Method to obtain the service for which we are asserting this ticket is
     * valid for.
     * 
     * @return the service for which we are asserting this ticket is valid for.
     */
    Service getService();

}
