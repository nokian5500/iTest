package test.portal;

import org.testng.annotations.Test;
import test.TestBase;
import test.portal.services.authorities.interaction.AssignSocialAssistanceForChildBirth;
import test.portal.services.authorities.interaction.CertificateOfLandSize;
import test.portal.services.authorities.interaction.Subsidy;
import test.portal.services.identity.citizenship.residense.InternationalPassport;
import test.portal.services.identity.citizenship.residense.Registration;
import test.portal.services.police.traffic.CriminalRecord;
import test.portal.services.police.traffic.RegisterUsedCar;
import test.portal.services.taxes.CertificateFromUnifiedRegister;
import test.portal.services.taxes.PensionAmountCertificate;
import test.portal.services.taxes.PersonalIncomeCertificate;

/**
 * Created by Slame on 15.02.2016.
 */
public class TestSuite extends TestBase {

	AssignSocialAssistanceForChildBirth assignSocialAssistanceForChildBirth = new AssignSocialAssistanceForChildBirth();
	CertificateOfLandSize certificateOfLandSize = new CertificateOfLandSize();
	Subsidy subsidy = new Subsidy();
	InternationalPassport internationalPassport = new InternationalPassport();
	Registration registration = new Registration();
	CriminalRecord criminalRecord = new CriminalRecord();
	RegisterUsedCar registerUsedCar = new RegisterUsedCar();
	CertificateFromUnifiedRegister certificateFromUnifiedRegister = new CertificateFromUnifiedRegister();
	PensionAmountCertificate pensionAmountCertificate = new PensionAmountCertificate();
	PersonalIncomeCertificate personalIncomeCertificate = new PersonalIncomeCertificate();

	@Test(enabled = true, groups = {"authInteraction", "AssignSocialAssistanceForChildBirth"}, priority = 1)
	public void A1() throws Exception {
		//Призначення соціальної допомоги при народженні дитини через національного оператора поштового зв'язку
		assignSocialAssistanceForChildBirth.successMessagesServicesByPostOffice(driver);
	}

	@Test(enabled = true, groups = {"authInteraction", "AssignSocialAssistanceForChildBirth"}, priority = 2)
	public void A2() throws Exception {
		//Призначення соціальної допомоги при народженні дитини на рахунок у банку
		assignSocialAssistanceForChildBirth.successMessagesServicesByAccountBank();
	}

	@Test(enabled = true, groups = {"authInteraction", "CertificateOfLandSize"}, priority = 3)
	public void A3() throws Exception {
		//
		certificateOfLandSize.certificateOfLandSizeTest();
	}

	@Test(enabled = true, groups = {"authInteraction", "Subsidy"}, priority = 4)
	public void A4() throws Exception {
		//
		subsidy.dnipropetrovskSubsidyTest();
	}

	@Test(enabled = true, groups = {"identity", "InternationalPassport"}, priority = 5)
	public void B1() throws Exception {
		//
		internationalPassport.dnipropetrovskInternationalPassportTest();
	}

	@Test(enabled = true, groups = {"identity", "Registration"}, priority = 6)
	public void B2() throws Exception {
		//
		registration.unregisterFromCurrentAddressTest();
	}

	@Test(enabled = true, groups = {"police", "CriminalRecord"}, priority = 7)
	public void C1() throws Exception {
		//
		criminalRecord.dnipropetrovskCriminalRecord();
	}

	@Test(enabled = true, groups = {"police", "RegisterUsedCar"}, priority = 8)
	public void C2() throws Exception {
		//
		registerUsedCar.registerUsedCarTest();
	}

	@Test(enabled = true, groups = {"taxes", "CertificateFromUnifiedRegister"}, priority = 9)
	public void D1() throws Exception {
		//
		certificateFromUnifiedRegister.certificateFromURSelf();
	}

	@Test(enabled = true, groups = {"taxes", "CertificateFromUnifiedRegister"}, priority = 10)
	public void D2() throws Exception {
		//
		certificateFromUnifiedRegister.certificateFromURByOtherCompany();
	}

	@Test(enabled = true, groups = {"taxes", "PensionAmountCertificate"}, priority = 11)
	public void D3() throws Exception {
		//
		pensionAmountCertificate.personalIncomeCertificateTest();
	}

	@Test(enabled = true, groups = {"taxes", "PersonalIncomeCertificate"}, priority = 11)
	public void D4() throws Exception {
		//
		personalIncomeCertificate.personalIncomeCertificateTest();
	}
}
