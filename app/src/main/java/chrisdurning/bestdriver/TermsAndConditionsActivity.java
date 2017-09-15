package chrisdurning.bestdriver;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TermsAndConditionsActivity extends AbsRuntimePermission {
    private static final int REQUEST_PERMISSION = 10;

    private TextView mTerms;
    private Button mAcceptTerms;
    private SharedPreferences mSettings = null;
    private boolean isTermsAccepted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make sure all suppressed data is removed
        Utility.removeStringFromSharedPreferences(getApplicationContext(),"callTimesReceived");
        Utility.removeStringFromSharedPreferences(getApplicationContext(),"smsTimesReceived");
        Utility.removeStringFromSharedPreferences(getApplicationContext(),"smsNumbers");
        Utility.removeStringFromSharedPreferences(getApplicationContext(),"smsMessages");
        Utility.removeStringFromSharedPreferences(getApplicationContext(),"numbers");

        Log.i("Terms&Conds", "onCreate - RecogServiceStarted");
        startService(new Intent(this, ActivityRecognitionService.class));

        mSettings = getSharedPreferences("pref", MODE_PRIVATE);

        Utility.putBooleanInPreferences(getApplicationContext(), true, "passenger");
        Utility.putBooleanInPreferences(getApplicationContext(), true, "miles");

        isTermsAccepted = mSettings.getBoolean("accept", false);

        if (isTermsAccepted) {
            termsAccepted();
        }

        setContentView(R.layout.activity_terms_and_conditions);

        mTerms = (TextView) findViewById(R.id.terms_and_cond);
        mTerms.setText("These terms and conditions create a contract between you and Apple (the “Agreement”). Please read the Agreement carefully. To confirm your understanding and acceptance of the Agreement, click “Agree.”\n" +
                "\n" +
                "A. INTRODUCTION TO OUR SERVICES\n" +
                "\n" +
                "This Agreement governs your use of Apple’s services (“Services”), through which you can buy, get, license, rent or subscribe to media, apps (“Apps”), and other in-app services (“Content”). Our Services are: iTunes Store, App Store, iBooks Store, Apple Music, and Apple News. By creating an account for use of the Services in Ireland you are specifying that as your country of residence for tax purposes (“Home Country”), and our Services are available for your use in your Home Country. To use our Services, you need compatible hardware, software (latest version recommended and sometimes required) and Internet access (fees may apply). Our Services’ performance may be affected by these factors.\n" +
                "\n" +
                "B. USING OUR SERVICES\n" +
                "\n" +
                "PAYMENTS, TAXES, AND REFUNDS\n" +
                "\n" +
                "You can acquire Content on our Services for free or for a charge, either of which is referred to as a “Transaction.” By each Transaction you acquire a license to use the Content only. Each Transaction is an electronic contract between you and Apple, and/or you and the entity providing the Content on our Services. However, if you are a customer of Apple Distribution International and you acquire an App or a book, Apple Distribution International is the merchant of record; this means that you acquire the Content from Apple Distribution International, but it is licensed by the App Provider (as defined below) or book publisher. When you make your first Transaction, we will ask you to choose how frequently we should ask for your password for future Transactions. If you enable Touch ID for Transactions, we will ask you to authenticate all Transactions with your fingerprint. Manage your password settings at any time by following these instructions: https://support.apple.com/en-us/HT204030. Apple will charge your payment method (such as your credit card, debit card, gift card/code, or other method available in your Home Country) for any paid Transactions, including any applicable taxes. If you pre-order Content, you will be charged when the Content is delivered to you (unless you cancel prior to the Content’s availability). For details about how Transactions are billed, please visit http://support.apple.com/kb/HT5582. You agree to receipt of all invoices in an electronic format, which may include email. Content prices may change at any time. If technical problems prevent or unreasonably delay delivery of Content, your exclusive and sole remedy is either replacement of the Content or refund of the price paid, as determined by Apple. From time to time, Apple may refuse a refund request if we find evidence of fraud, refund abuse, or other manipulative behavior that entitles Apple to a corresponding counterclaim. Terms related to gift cards/codes are available here: https://www.apple.com/legal/internet-services/itunes/giftcards/.\n" +
                "\n" +
                "Right of cancellation: If you choose to cancel your order, you may do so within 14 days from when you received your receipt without giving any reason.\n" +
                "\n" +
                "To cancel your order, you must inform us of your decision. To ensure immediate processing we recommend you use Report a Problem to cancel all items with the exception of Apple Music, iTunes Match, Season Pass, Multi-Pass, and unredeemed iTunes Gifts purchased directly from Apple. The excepted items can be cancelled by contacting iTunes Support. You also have the right to inform us using the model cancellation form below or by making any other clear statement. If you use Report a Problem we will communicate acknowledgement of receipt of your cancellation to you without delay.\n" +
                "\n" +
                "To meet the cancellation deadline, you must send your communication of cancellation before the 14-day period has expired.\n" +
                "\n" +
                "Effects of cancellation: We will reimburse you no later than 14 days from the day on which we receive your cancellation notice. We will use the same means of payment as you used for the transaction, and you will not incur any fees for such reimbursement.\n" +
                "\n" +
                "Exception to the right of cancellation: You cannot cancel your order for the supply of Content if the delivery has started upon your request and acknowledgement that you thereby lose your cancellation right.\n" +
                "\n" +
                "Model cancellation form:\n" +
                "\n" +
                "- To Apple Distribution International, Hollyhill Industrial Estate, Hollyhill, Cork, Republic of Ireland:\n" +
                "\n" +
                "- I hereby give notice that I withdraw from my contract for the following:\n" +
                "\n" +
                "[INSERT ORDER ID, ITEM, ARTIST AND TYPE]\n" +
                "\n" +
                "- Ordered on [INSERT DATE] / received on [INSERT DATE]\n" +
                "\n" +
                "- Name of consumer\n" +
                "\n" +
                "- Address of consumer\n" +
                "\n" +
                "- Email address of consumer (optional)\n" +
                "\n" +
                "- Date\n" +
                "\n" +
                "APPLE ID\n" +
                "\n" +
                "Using our Services and accessing your Content requires an Apple ID. An Apple ID is the account you use across Apple’s ecosystem. Your Apple ID is valuable, and you are responsible for maintaining its confidentiality and security. Apple is not responsible for any losses arising from the unauthorized use of your Apple ID. Please contact Apple if you suspect that your Apple ID has been compromised.\n" +
                "\n" +
                "You must be age 13 (or equivalent minimum age in your Home Country, as set forth in the registration process) to create an Apple ID and use our Services. Apple IDs for persons under this age can be created by a parent or legal guardian using Family Sharing or by an approved educational institution.\n" +
                "\n" +
                "PRIVACY\n" +
                "\n" +
                "Your use of our Services is subject to Apple’s Privacy Policy, which is available at https://www.apple.com/legal/privacy/.\n" +
                "\n" +
                "SERVICES AND CONTENT USAGE RULES\n" +
                "\n" +
                "Your use of the Services and Content must follow the rules set forth in this section (“Usage Rules”). Any other use of the Services and Content is a material breach of this Agreement. Apple may monitor your use of the Services and Content to ensure that you are following these Usage Rules.\n" +
                "\n" +
                "All Services:\n" +
                "\n" +
                "- You may use the Services and Content only for personal, noncommercial purposes (except as set forth in the App Store Content section below).\n" +
                "\n" +
                "- Apple’s delivery of Content does not transfer any promotional use rights to you, and does not constitute a grant or waiver of any rights of the copyright owners.\n" +
                "\n" +
                "- You can use Content from up to five different Apple IDs on each device.\n" +
                "\n" +
                "- It is your responsibility not to lose, destroy, or damage Content once downloaded. We encourage you to back up your Content regularly.\n" +
                "\n" +
                "- You may not tamper with or circumvent any security technology included with the Services.\n" +
                "\n" +
                "- You may access our Services only using Apple’s software, and may not modify or use modified versions of such software.\n" +
                "\n" +
                "- Video Content requires an HDCP connection.\n" +
                "\n" +
                "iTunes Store Content:\n" +
                "\n" +
                "- You can use Digital Rights Management (DRM)-free Content on a reasonable number of compatible devices that you own or control. DRM-protected Content can be used on up to five computers and any number of devices that you sync to from those computers.\n" +
                "\n" +
                "- Content rentals are viewable on a single device at a time, and must be played within 30 days, and completed within 24 to 48 hours of the start of play depending on the Content offered on the Services in your Home Country (stopping, pausing or restarting does not extend this period).\n" +
                "\n" +
                "- You may burn an audio playlist to CD for listening purposes up to seven times (this limitation does not apply to DRM-free Content). You may use the audio CD to which you have burned your Content in the same ways in which you may use an audio CD purchased from a retail store, subject to local copyright laws.\n" +
                "\n" +
                "App Store Content:\n" +
                "\n" +
                "- The term “App” includes apps, iMessage and Apple Watch apps, in-app purchases, extensions (such as keyboards), stickers, and subscriptions made available in an app.\n" +
                "\n" +
                "- You can use Apps on any device that you own or control.\n" +
                "\n" +
                "- Individuals acting on behalf of a commercial enterprise, governmental organization or educational institution (an “Enterprise”) may download and sync Apps for use by either (i) a single individual on one or more devices owned or controlled by an Enterprise; or (ii) multiple individuals on a single shared device owned or controlled by an Enterprise. For the sake of clarity, each device used serially or collectively by multiple users requires a separate license." + "\n" + "\n" + "\n");

        mAcceptTerms = (Button) findViewById(R.id.accept_terms);
        mAcceptTerms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                requestAppPermissions(new String[]{
                                "android.permission.READ_CONTACTS",
                                "android.permission.WRITE_EXTERNAL_STORAGE",
                                "android.permission.WRITE_CONTACTS",
                                "android.permission.ACCESS_FINE_LOCATION",
                                "android.permission.ACCESS_COARSE_LOCATION",
                                "android.permission.READ_PHONE_STATE",
                                "android.permission.READ_SMS",
                                "android.permission.RECEIVE_SMS"},
                        R.string.msg,REQUEST_PERMISSION);

                Utility.putBooleanInPreferences(getApplicationContext(),true,"pref");
                termsAccepted();
                Log.i("************", "onClick: ");
            }
        });

    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }


    private void termsAccepted() {
        Intent intent = new Intent(TermsAndConditionsActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
