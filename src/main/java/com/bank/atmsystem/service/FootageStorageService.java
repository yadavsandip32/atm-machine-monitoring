package com.bank.atmsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.ZoneOffset;

@Service
public class FootageStorageService {

    private final String storageDirectory = "/path/to/storage/directory"; // We can change this to our desired path

    /**
     * Saves the uploaded footage to the specified storage location.
     *
     * @param footage The footage file to be uploaded.
     * @return The file path where the footage is stored.
     * @throws IOException If an error occurs during file storage.
     */
    public String storeFootage(MultipartFile footage) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + footage.getOriginalFilename();
        Path targetLocation = Paths.get(storageDirectory).resolve(fileName);
        Files.copy(footage.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return targetLocation.toString();
    }

    /**
     * Retrieves footage files within a specified time range.
     *
     * @param startTime The start of the time range.
     * @param endTime   The end of the time range.
     * @return A list of file paths that match the specified time range.
     */
    public List<String> getFootageByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        List<String> matchingFootage = new ArrayList<>();
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(storageDirectory));
            for (Path path : stream) {
                // Extract timestamp from the filename (assuming the format includes timestamp)
                long timestamp = Long.parseLong(path.getFileName().toString().split("_")[0]);
                LocalDateTime fileTime = LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.UTC);
                if ((fileTime.isEqual(startTime) || fileTime.isAfter(startTime)) &&
                        (fileTime.isEqual(endTime) || fileTime.isBefore(endTime))) {
                    matchingFootage.add(path.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the error as needed
        }
        return matchingFootage;
    }

    /**
     * Deletes footage based on the provided file path.
     *
     * @param filePath The path of the footage to be deleted.
     * @return true if deletion was successful, false otherwise.
     */
    public boolean deleteFootage(String filePath) {
        try {
            return Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace(); // Handle the error as needed
            return false;
        }
    }
}
