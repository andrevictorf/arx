/*
 * ARX: Powerful Data Anonymization
 * Copyright 2012 - 2016 Fabian Prasser, Florian Kohlmayer and contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.deidentifier.arx.risk;

/**
 * Provides information about the occurrence of an HIPPA identifier
 * 
 * @author David Gassmann
 * @author Fabian Prasser
 * @author Florian Kohlmayer
 */
public class HIPAAIdentifierMatch {
    
    /**
     * Represents the HIPPA identifiers
     * 
     * @author David Gassmann
     * @author Fabian Prasser
     * @author Florian Kohlmayer
     */
    public enum HIPAAIdentifier {
                                 NAME('A'),
                                 GEOGRAPHIC_SUBDIVISION('B'),
                                 DATE('C'),
                                 TELEPHONE_NUMBER('D'),
                                 FAX_NUMBER('E'),
                                 EMAIL_ADDRESS('F'),
                                 SOCIAL_SECURITY_NUMBER('G'),
                                 MEDICAL_RECORD_NUMBER('H'),
                                 HEALTH_PLAN_BENEFICIARY_NUMBER('I'),
                                 ACCOUNT_NUMBER('J'),
                                 CERTIFICATE_NUMBER('K'),
                                 VEHICLE_IDENTIFIER('L'),
                                 DEVICE_IDENTIFIER('M'),
                                 URL('N'),
                                 IP('O'),
                                 BIOMETRIC_IDENTIFIER('P'),
                                 PHOTOGRAPH('Q'),
                                 OTHER('R');
                                 
        private final char category;
        
        HIPAAIdentifier(char category) {
            this.category = category;
        }
        
        int getCategory() {
            return category;
        }
    }
    
    /**
     * Represents the classifier for the HIPAA identifier.
     * @author David Gassmann
     * @author Fabian Prasser
     * @author Florian Kohlmayer
     */
    public enum MatchType {ATTRIBUTE_NAME,
                           ATTRIBUTE_VALUE
    }
    
    /** TODO */
    private final String          column;
    /** TODO */
    private final HIPAAIdentifier identifier;
    /** TODO */
    private final MatchType       matchType;
    /** TODO */
    private final String          value;
    /** TODO */
    private final String          instance;
    
    /**
     * Constructor.
     * 
     * @param column
     * @param identifier
     * @param type
     * @param value
     */
    HIPAAIdentifierMatch(String column, HIPAAIdentifier identifier, String instance, MatchType type, String value) {
        this.column = column;
        this.matchType = type;
        this.identifier = identifier;
        this.value = value;
        this.instance = instance;
    }
    
    /**
     * The column name where the identifier was found
     * @return The column name
     */
    public String getColumn() {
        return column;
    }
    
    /**
     * The confidence of the discovery, or null if the
     * match type is ATTRIBUTE_NAME
     * 
     * @return The value which caused the warning
     */
    public Double getConfidence() {
        try {
            return Double.valueOf(value);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * The identifier which was found
     * @return The found identifier
     */
    public HIPAAIdentifier getIdentifier() {
        return identifier;
    }
    
    /**
     * The instance of the identifier
     * @return
     */
    public String getInstance() {
        return instance;
    }
    
    /**
     * The classifier (column name or instance)
     * @return
     */
    public MatchType getMatchType() {
        return matchType;
    }

    /**
     * The value which caused the identification
     * @return The value which caused the warning
     */
    public String getValue() {
        if (getConfidence() != null) {
            return null;
        } else {
            return value;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HIPAAIdentifierMatch {\n");
        builder.append(" - Column: ").append(getColumn()).append("\n");
        builder.append(" - Identifier: ").append(getIdentifier()).append("\n");
        builder.append(" - Instance: ").append(getInstance()).append("\n");
        builder.append(" - Match type: ").append(getMatchType()).append("\n");
        builder.append(" - Value: ").append(getValue()).append("\n");
        builder.append("}");
        return builder.toString();
    }
}
