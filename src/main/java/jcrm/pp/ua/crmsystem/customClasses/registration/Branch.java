package jcrm.pp.ua.crmsystem.customClasses.registration;

public enum Branch {

    //ABCDEFGHIJKLMNOPQRSTUVWXYZ

    AIC{
        public String branch(){
            return "Agro-industrial complex";
        }
    },
    ARCHITECTURAL_AND_DESIGN{
        public String branch(){
            return "Architectural and Design offices";
        }
    },
    AUTOMOTIVE_AND_AUTO_BUSINESS{
        public String branch(){
            return "Automotive and Auto Business";
        }
    },
    BANKS{
        public String branch(){
            return "Banks";
        }
    },
    BEAUTY_FITNES_SPORT{
        public String branch(){
            return "Beauty / Fitness / Sports";
        }
    },
    BUILDING{
        public String branch(){
            return "Building";
        }
    },
    CONSULTING_AUDIT{
        public String branch(){
            return "Consulting / Audit";
        }
    },
    EDUCATION{
        public String branch(){
            return "Education";
        }
    },
    ELECTRONICS{
        public String branch(){
            return "Electronics and Electrical Engineering";
        }
    },
    ENERGY{
        public String branch(){
            return "Energy";
        }
    },
    FINANCIAL{
        public String branch(){
            return "Financial services";
        }
    },
    FMCG{
        public String branch(){
            return "Fast Moving Consumer Goods";
        }
    },
    GOVERNMENT{
        public String branch(){
            return "Government sector";
        }
    },
    HOTELS_RESTAURANTS{
        public String branch(){
            return "Hotels / Restaurants / Entertaining complexes";
        }
    },
    HR{
        public String branch(){
            return "Human Resource";
        }
    },
    INDUSTRY{
        public String branch(){
            return "Industry and Manufacturing";
        }
    },
    INSURANCE{
        public String branch(){
            return "Insurance";
        }
    },
    INTERNET{
        public String branch(){
            return "Internet";
        }
    },
    IT_SOFTWARE_DEVELOPMENT{
        public String branch(){
            return "IT - Software Development";
        }
    },
    IT_CONSULTING{
        public String branch(){
            return "IT - Consulting / Services / Production of equipment";
        }
    },
    LEGAL{
        public String branch(){
            return "Legal services";
        }
    },
    MEDIA{
        public String branch(){
            return "Media";
        }
    },
    MEDICINE_AND_HEALTHCARE{
        public String branch(){
            return "Medicine and Health Care";
        }
    },
    NGO{
        public String branch(){
            return "Non-governmental organizations";
        }
    },
    OTHER{
        public String branch(){
            return "Other";
        }
    },
    PHARMACEUTICALS{
        public String branch(){
            return "Pharmaceuticals";
        }
    },
    PR{
        public String branch(){
            return "Advertising and PR-services";
        }
    },
    PUBLISHERS_AND_POLYGRAPHY{
        public String branch(){
            return "Publishers and Polygraphy";
        }
    },
    REAL_ESTATE{
        public String branch(){
            return "Real Estate and Development";
        }
    },
    RETAIL{
        public String branch(){
            return "Retail";
        }
    },
    SECURITY{
        public String branch(){
            return "Security & Safety";
        }
    },
    SERVICES_FOR_BUSINESS{
        public String branch(){
            return "Services for business - other";
        }
    },
    SERVICES_FOR_POPULATION{
        public String branch(){
            return "Services for the population - other";
        }
    },
    SHOW_BUSINESS{
        public String branch(){
            return "Show Business";
        }
    },
    TELECOMMUNICATIONS{
        public String branch(){
            return "Telecommunications / Communications";
        }
    },
    TOURISM{
        public String branch(){
            return "Tourism / Travel / Passenger Transportation";
        }
    },
    TRADE{
        public String branch(){
            return "Trade wholesale / Distribution / Import-Export";
        }
    },

    TRANSPORT{
        public String branch(){
            return "Transport and Logistic";
        }
    };

    public abstract String branch();
}
