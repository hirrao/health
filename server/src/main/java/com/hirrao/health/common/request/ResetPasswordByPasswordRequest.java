package com.hirrao.health.common.request;

public record ResetPasswordByPasswordRequest(String oldPassword,
                                             String newPassword) {
}
