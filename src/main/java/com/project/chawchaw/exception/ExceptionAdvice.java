package com.project.chawchaw.exception;

import com.project.chawchaw.response.CommonResult;
import com.project.chawchaw.service.ResponseService;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    private final MessageSource messageSource;
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
//        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")),getMessage("unKnown.msg"));
//    }

    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult loginFailureException(){
        return responseService.getFailResult(Integer.valueOf(getMessage("loginFail.code")),getMessage("loginFail.msg"));
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userAlreadyExistException(){
        return responseService.getFailResult(Integer.valueOf(getMessage("alreadyUserExist.code")),getMessage("alreadyUserExist.msg"));
    }
    @ExceptionHandler(FollowAlreadyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult followAlreadyExistException(){
        return responseService.getFailResult(Integer.valueOf(getMessage("followAlreadyExist.code")),getMessage("followAlreadyExist.msg"));
    }

    @ExceptionHandler(InvalidateProviderException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult invalidateProviderException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("InvalidateProvider.code")),getMessage("InvalidateProvider.msg"));
    }


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResult userNotFoundException(HttpServletRequest request, UserNotFoundException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")),getMessage("userNotFound.msg"));

    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResult resourceNotFoundException(HttpServletRequest request, ResourceNotFoundException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("resourceNotFound.code")),getMessage("resourceNotFound.msg"));

    }

    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResult countryNotFoundException(HttpServletRequest request, UserNotFoundException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("countryNotFound.code")),getMessage("countryFound.msg"));

    }
    @ExceptionHandler(LanguageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResult languageFoundException(HttpServletRequest request, UserNotFoundException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("languageNotFound.code")),getMessage("languageNotFound.msg"));

    }

    @ExceptionHandler(FollwNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResult followNotFoundException(HttpServletRequest request, UserNotFoundException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("followNotFound.code")),getMessage("followNotFound.msg"));

    }
//    @ExceptionHandler(CUsernameSigninFailedException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    protected  CommonResult usernameSigninFailedException(HttpServletRequest request,CUsernameSigninFailedException e){
//        return responseService.getFailResult(Integer.valueOf(getMessage("usernameSigninFailed.code")),getMessage("usernameSigninFailed.msg"));
//    }
//    @ExceptionHandler(CResourceNotExistException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    protected  CommonResult resourceNotExistException(HttpServletRequest request,CResourceNotExistException e){
//        return responseService.getFailResult(Integer.valueOf(getMessage("resourceNotExist.code")),getMessage("resourceNotExist.msg"));
//    }
//    @ExceptionHandler(CNotOwnerException.class)
//    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
//    protected  CommonResult notOwnerException(HttpServletRequest request,CNotOwnerException e){
//        return responseService.getFailResult(Integer.valueOf(getMessage("notOwner.code")),getMessage("notOwner.msg"));
//    }
//
    @ExceptionHandler(AuthenticationEntryPointException.class)
    protected CommonResult authenticationEntryPointException(HttpServletRequest request,AuthenticationEntryPointException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("entryPointException.code")),getMessage("entryPointException.msg"));
    }
//    @ExceptionHandler(AccessDeniedException.class)
//    protected CommonResult accessDeniedException(HttpServletRequest request,AccessDeniedException e){
//            return responseService.getFailResult(Integer.valueOf(getMessage("accessDenied.code")), getMessage("accessDenied.msg"));
//
//    }
//    @ExceptionHandler(COrderNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    protected CommonResult orderNotFound(HttpServletRequest request,AccessDeniedException e){
//        return responseService.getFailResult(Integer.valueOf(getMessage("orderNotFound.code")), getMessage("orderNotFound.msg"));
//
//    }
//    @ExceptionHandler(CNotEnoughStockException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    protected CommonResult notEnoughStock(){
//        return responseService.getFailResult(Integer.valueOf(getMessage("notEnoughStock.code")), getMessage("notEnoughStock.msg"));
//
//    }
//    @ExceptionHandler(CShoesAlreadyExistException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    protected CommonResult shoesAlreadyExist(){
//        return responseService.getFailResult(Integer.valueOf(getMessage("shoesAlreadyExist.code")), getMessage("shoesAlreadyExist.msg"));
//
//    }
    @ExceptionHandler(CommunicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult communicationException(HttpServletRequest request, CommunicationException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("communicationError.code")), getMessage("communicationError.msg"));
    }
//    @ExceptionHandler(CUserNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    protected CommonResult messageNotFoundException(HttpServletRequest request,CUserNotFoundException e){
//        return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")),getMessage("userNotFound.msg"));
//
//    }




    private String getMessage(String code){
        return getMessage(code,null);
    }
    private String getMessage(String code, Object[] args){
        return messageSource.getMessage(code,args, LocaleContextHolder.getLocale());
    }



}