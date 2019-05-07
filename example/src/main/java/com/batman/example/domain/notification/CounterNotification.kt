package com.batman.example.domain.notification

import com.batman.batdroid.domain.notification.BatmanNotification

// <editor-fold desc="Define a notification for the NotificationPublisher">
class CounterNotification(val data: Long): BatmanNotification()
// </editor-fold>