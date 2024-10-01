package dev.alex96jvm.selmag.manager.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewProductPayload(String title, String details) {
}
