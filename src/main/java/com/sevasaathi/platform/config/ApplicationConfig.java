package com.sevasaathi.platform.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Main application configuration for SevaSaathi Platform.
 * 
 * This configuration class provides:
 * - CORS configuration for cross-origin requests
 * - Application-specific properties binding
 * - Common bean definitions
 */
@Configuration
public class ApplicationConfig {

    /**
     * CORS configuration to allow frontend applications to access the API.
     * 
     * @return CorsConfigurationSource with appropriate CORS settings
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Allow specific origins (configure based on environment)
        configuration.setAllowedOriginPatterns(Arrays.asList(
            "http://localhost:3000",  // React development server
            "http://localhost:8080",  // Alternative development port
            "https://*.sevasaathi.com" // Production domains
        ));
        
        // Allow specific HTTP methods
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));
        
        // Allow specific headers
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization", "Content-Type", "X-Requested-With", 
            "Accept", "Origin", "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
        ));
        
        // Allow credentials (cookies, authorization headers)
        configuration.setAllowCredentials(true);
        
        // Cache preflight response for 1 hour
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }

    /**
     * Application-specific properties configuration.
     */
    @Configuration
    @ConfigurationProperties(prefix = "app")
    public static class AppProperties {
        
        private Business business = new Business();
        private Cache cache = new Cache();
        
        // Getters and setters
        public Business getBusiness() { return business; }
        public void setBusiness(Business business) { this.business = business; }
        
        public Cache getCache() { return cache; }
        public void setCache(Cache cache) { this.cache = cache; }
        
        /**
         * Business logic configuration properties.
         */
        public static class Business {
            private Double defaultSearchRadiusKm = 10.0;
            private Double maxSearchRadiusKm = 50.0;
            private Integer bookingAdvanceDays = 30;
            private Integer ratingScaleMax = 5;
            
            // Getters and setters
            public Double getDefaultSearchRadiusKm() { return defaultSearchRadiusKm; }
            public void setDefaultSearchRadiusKm(Double defaultSearchRadiusKm) { 
                this.defaultSearchRadiusKm = defaultSearchRadiusKm; 
            }
            
            public Double getMaxSearchRadiusKm() { return maxSearchRadiusKm; }
            public void setMaxSearchRadiusKm(Double maxSearchRadiusKm) { 
                this.maxSearchRadiusKm = maxSearchRadiusKm; 
            }
            
            public Integer getBookingAdvanceDays() { return bookingAdvanceDays; }
            public void setBookingAdvanceDays(Integer bookingAdvanceDays) { 
                this.bookingAdvanceDays = bookingAdvanceDays; 
            }
            
            public Integer getRatingScaleMax() { return ratingScaleMax; }
            public void setRatingScaleMax(Integer ratingScaleMax) { 
                this.ratingScaleMax = ratingScaleMax; 
            }
        }
        
        /**
         * Cache configuration properties.
         */
        public static class Cache {
            private Ttl ttl = new Ttl();
            
            public Ttl getTtl() { return ttl; }
            public void setTtl(Ttl ttl) { this.ttl = ttl; }
            
            public static class Ttl {
                private Long userProfile = 3600L;
                private Long providerProfile = 1800L;
                private Long searchResults = 300L;
                private Long recommendations = 600L;
                
                // Getters and setters
                public Long getUserProfile() { return userProfile; }
                public void setUserProfile(Long userProfile) { this.userProfile = userProfile; }
                
                public Long getProviderProfile() { return providerProfile; }
                public void setProviderProfile(Long providerProfile) { this.providerProfile = providerProfile; }
                
                public Long getSearchResults() { return searchResults; }
                public void setSearchResults(Long searchResults) { this.searchResults = searchResults; }
                
                public Long getRecommendations() { return recommendations; }
                public void setRecommendations(Long recommendations) { this.recommendations = recommendations; }
            }
        }
    }
}