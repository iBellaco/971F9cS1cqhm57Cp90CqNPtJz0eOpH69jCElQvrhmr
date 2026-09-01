sed -i '/isOverlayExpanded = expanded/a \
                                if (expanded) {\
                                    params.flags = params.flags and WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE.inv()\
                                } else {\
                                    params.flags = params.flags or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE\
                                }' app/src/main/java/com/example/service/FloatingAssistantService.kt
