package com.xforce.pethealth.account_management.infrastructure.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){ super(message); }
}