package br.com.simplifiedpicpay.transaction.application.ports;

import java.math.BigDecimal;

/**
 * Interface defines the service contract to verify the external authorization
 * Exit port of application layer
 */
public interface IAuthorizationPort {
    /**
     * Consult an external service to verify whether the transaction is allowed or not
     * @param senderId: The sender ID, it can be necessary to the token
     * @param amount: The transaction value.
     * @return true if the authorized, false otherwise.
     */

    boolean isAuthorized(Long senderId, BigDecimal amount);
}