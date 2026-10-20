package de.saki.enerflow.adapter.web.dto;

/**
 * JSON view of the device_config keys the manager may edit. Numbers are
 * already parsed. The entity stores strings, because the table is a
 * key-value store. The service is the place that converts between them.
 *
 * Data transfer object for reading and updating runtime-configurable
 * EnerFlow parameters (device_config table) via the Manager UI.
 *
 * @author saki
 */
public record DeviceConfigDto(
        int pvSurplusThresholdWatts,
        int batterySocThresholdPercent,
        double hotwaterSetpointElevatedCelsius,
        double hotwaterSetpointNormalCelsius,
        double hotwaterTankVolumeLiters,
        int snapshotRetentionDays
) {
}