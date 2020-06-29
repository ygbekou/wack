package com.wack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company extends BaseEntity {

	@Id
	@Column(name = "COMPANY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	@Column(name = "META_KEYWORD")
	private String metaKeyword;
	@Column(name = "META_DESCRIPTION")
	private String metaDescription;
	private String address;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PHONE")
	private String phone;
	private String phone2;
	private String fax;
	private String logo;
	private String favicon;
	@Column(name = "BACKGROUND_SLIDER")
	private String backgroundSlider;
	@Column(name = "BANNER_CONTENT_HEADER")
	private String bannerContentHeader;
	@Column(name = "BANNER_CONTENT_PARAGRAPH")
	private String bannerContentParagraph;
	private String copyright;
	@Column(name = "TWITTER_API")
	private String twitterApi;
	@Column(name = "GOOGLE_MAP")
	private String googleMap;
	@Column(name = "FACEBOOK_URL")
	private String facebookUrl;
	
	@Column(name = "GOOGLE_PLUS_URL")
	private String googlePlusUrl;
	@Column(name = "LINKED_IN_URL")
	private String linkedInUrl;	
	@Column(name = "TWITTER_URL")
	private String twitterUrl;
	@Column(name = "INSTAGRAM_URL")
	private String instagramUrl;
	@Column(name = "FOOTER_PARAGRAPH1")
	private String footerParagraph1;
	@Column(name = "FOOTER_PARAGRAPH2")
	private String footerParagraph2;
	@Column(name = "FOOTER_PARAGRAPH3")
	private String footerParagraph3;
	private String language;
	@Column(name = "FROM_EMAIL")
	private String fromEmail;
	@Column(name = "TO_EMAIL")
	private String toEmail;
	@Column(name = "HOURS_OF_OPERATION")
	private String hoursOfOperation;
	private Double longitude;
	private Double latitude;
	@Column(name = "HOME_IMAGE")
	private String homeImage; 
	@Column(name = "FOOTER_TYPE")
	private String footerType;
	
	private int status;
	// site themes

	@Column(name = "FIXED_MENU")
	private int fixedMenu;

	public String getLinkedInUrl() {
		return linkedInUrl;
	}

	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}

	@Column(name = "LEFT_TO_RIGHT")
	private int leftToRight;

	@Column(name = "HEADER_TEXT_POSITION")
	private int headerTextPosition;

	@Column(name = "HEADER_IMAGE_TYPE")
	private String headerImageType;

	@Column(name = "THEME_COLOR")
	private String themeColor;
	
	@Column (name="DISPLAY_MISSION")
	private boolean displayMission = false;
	@Column (name="DISPLAY_TESTIMONIAL")
	private boolean displayTestimonial = false;
	@Column (name="DISPLAY_SERVICES")
	private boolean displayServices = false;
	@Column (name="DISPLAY_CLIENT_LOGOS")
	private boolean displayClientLogos = false;
	@Column (name="DISPLAY_TEAMS")
	private boolean displayTeams = false;
	@Column (name="DISPLAY_EXPERTISE")
	private boolean displayExpertise = false;	
	@Column (name="DISPLAY_AUTHOR")
	private boolean displayAuthor = false;
	@Column (name="DISPLAY_FEATURED")
	private boolean displayFeatured = false;
	@Column (name="DISPLAY_BLOGS")
	private boolean displayBlogs = false;
	@Column (name="DISPLAY_SHORT_LANG")
	private boolean displayShortLang = false;
	@Column (name="DISPLAY_TOOLBAR")
	private boolean displayToolbar = false;
	@Column (name="DISPLAY_FOOTER_CONTACT")
	private boolean displayFooterContact = false;

	@Column (name="DISPLAY_FEATURED_BLOGS")
	private boolean displayFeaturedBlogs = false;
	
	public String getFooterType() {
		return footerType;
	}

	public void setFooterType(String footerType) {
		this.footerType = footerType;
	}

	public String getGooglePlusUrl() {
		return googlePlusUrl;
	}

	public void setGooglePlusUrl(String googlePlusUrl) {
		this.googlePlusUrl = googlePlusUrl;
	}

	public boolean isDisplayFeaturedBlogs() {
		return displayFeaturedBlogs;
	}

	public void setDisplayFeaturedBlogs(boolean displayFeaturedBlogs) {
		this.displayFeaturedBlogs = displayFeaturedBlogs;
	}

	public boolean isDisplayFooterContact() {
		return displayFooterContact;
	}

	public void setDisplayFooterContact(boolean displayFooterContact) {
		this.displayFooterContact = displayFooterContact;
	}

	public boolean isDisplayToolbar() {
		return displayToolbar;
	}

	public void setDisplayToolbar(boolean displayToolbar) {
		this.displayToolbar = displayToolbar;
	}

	public boolean isDisplayShortLang() {
		return displayShortLang;
	}

	public void setDisplayShortLang(boolean displayShortLang) {
		this.displayShortLang = displayShortLang;
	}

	public boolean isDisplayMission() {
		return displayMission;
	}

	public void setDisplayMission(boolean displayMission) {
		this.displayMission = displayMission;
	}

	public boolean isDisplayTestimonial() {
		return displayTestimonial;
	}

	public void setDisplayTestimonial(boolean displayTestimonial) {
		this.displayTestimonial = displayTestimonial;
	}

	public boolean isDisplayServices() {
		return displayServices;
	}

	public void setDisplayServices(boolean displayServices) {
		this.displayServices = displayServices;
	}

	public boolean isDisplayClientLogos() {
		return displayClientLogos;
	}

	public void setDisplayClientLogos(boolean displayClientLogos) {
		this.displayClientLogos = displayClientLogos;
	}

	public boolean isDisplayTeams() {
		return displayTeams;
	}

	public void setDisplayTeams(boolean displayTeams) {
		this.displayTeams = displayTeams;
	}

	public boolean isDisplayExpertise() {
		return displayExpertise;
	}

	public void setDisplayExpertise(boolean displayExpertise) {
		this.displayExpertise = displayExpertise;
	}

	public boolean isDisplayAuthor() {
		return displayAuthor;
	}

	public void setDisplayAuthor(boolean displayAuthor) {
		this.displayAuthor = displayAuthor;
	}

	public boolean isDisplayFeatured() {
		return displayFeatured;
	}

	public void setDisplayFeatured(boolean displayFeatured) {
		this.displayFeatured = displayFeatured;
	}

	public boolean isDisplayBlogs() {
		return displayBlogs;
	}

	public void setDisplayBlogs(boolean displayBlogs) {
		this.displayBlogs = displayBlogs;
	}

	public int getFixedMenu() {
		return fixedMenu;
	}

	public void setFixedMenu(int fixedMenu) {
		this.fixedMenu = fixedMenu;
	}

	public int getLeftToRight() {
		return leftToRight;
	}

	public void setLeftToRight(int leftToRight) {
		this.leftToRight = leftToRight;
	}

	public int getHeaderTextPosition() {
		return headerTextPosition;
	}

	public void setHeaderTextPosition(int headerTextPosition) {
		this.headerTextPosition = headerTextPosition;
	}

	public String getHeaderImageType() {
		return headerImageType;
	}

	public void setHeaderImageType(String headerImageType) {
		this.headerImageType = headerImageType;
	}

	public String getThemeColor() {
		return themeColor;
	}

	public void setThemeColor(String themeColor) {
		this.themeColor = themeColor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMetaKeyword() {
		return metaKeyword;
	}

	public void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getFavicon() {
		return favicon;
	}

	public void setFavicon(String favicon) {
		this.favicon = favicon;
	}

	public String getBackgroundSlider() {
		return backgroundSlider;
	}

	public void setBackgroundSlider(String backgroundSlider) {
		this.backgroundSlider = backgroundSlider;
	}

	public String getBannerContentHeader() {
		return bannerContentHeader;
	}

	public void setBannerContentHeader(String bannerContentHeader) {
		this.bannerContentHeader = bannerContentHeader;
	}

	public String getBannerContentParagraph() {
		return bannerContentParagraph;
	}

	public void setBannerContentParagraph(String bannerContentParagraph) {
		this.bannerContentParagraph = bannerContentParagraph;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getTwitterApi() {
		return twitterApi;
	}

	public void setTwitterApi(String twitterApi) {
		this.twitterApi = twitterApi;
	}

	public String getGoogleMap() {
		return googleMap;
	}

	public void setGoogleMap(String googleMap) {
		this.googleMap = googleMap;
	}

	public String getFacebookUrl() {
		return facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public String getInstagramUrl() {
		return instagramUrl;
	}

	public void setInstagramUrl(String instagramUrl) {
		this.instagramUrl = instagramUrl;
	}

	public String getFooterParagraph1() {
		return footerParagraph1;
	}

	public void setFooterParagraph1(String footerParagraph1) {
		this.footerParagraph1 = footerParagraph1;
	}

	public String getFooterParagraph2() {
		return footerParagraph2;
	}

	public void setFooterParagraph2(String footerParagraph2) {
		this.footerParagraph2 = footerParagraph2;
	}

	public String getFooterParagraph3() {
		return footerParagraph3;
	}

	public void setFooterParagraph3(String footerParagraph3) {
		this.footerParagraph3 = footerParagraph3;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHoursOfOperation() {
		return hoursOfOperation;
	}

	public void setHoursOfOperation(String hoursOfOperation) {
		this.hoursOfOperation = hoursOfOperation;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getHomeImage() {
		return homeImage;
	}

	public void setHomeImage(String homeImage) {
		this.homeImage = homeImage;
	}

}
