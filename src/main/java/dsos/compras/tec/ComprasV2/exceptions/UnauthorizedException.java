/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsos.compras.tec.ComprasV2.exceptions;

import dsos.compras.tec.ComprasV2.constans.AuthenticationConstans;

/**
 *
 * @author Oscar
 */
public class UnauthorizedException extends CustomException{
     public UnauthorizedException() {
        super(AuthenticationConstans.INVALID_TOKEN_MENSAJE_EXCEPTION);
    }
}
