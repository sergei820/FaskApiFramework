To run the framework:

    mvn test

Notes:
1. Test checkReceiveAssets() fails because asset "PURPLE" doesn't contain "AT".
Maybe "PURPLE" is correct value, but that's how I understood the logic, but maybe it's a defect.
I would ask for BA about it.
2. The next behavior looks like a defect alse:
GET /info with countries="WRONG_COUNTRY_CODE" returns all values. At the same time filtering works correctly
with an existing correct parameter value, for example countries="IND" returns "country_code": "IND" record only.


API DOCUMENTATION

Endpoint: /info

Description:
This API endpoint provides information about a payment with the specified ID and the SEP-0031 standard.
It returns details related to the payment, such as country-specific information and asset details.

Query Parameters:

countries:
    A string parameter specifying the countries for which to retrieve information.
    Multiple country codes can be provided separated by commas.
asset:
    A string parameter specifying the asset for which to retrieve information.
Example Request:
GET /api/v2/payments/edeab824-178e-4fd7-9bf0-bd88a6fd114a/sep0031/info?countries=WWC&asset=ATUSD

Example Response:
{
    "payment_id": "edeab824-178e-4fd7-9bf0-bd88a6fd114a",
    "standard": "SEP-0031",
    "country_info": {
        "WWC": {
            "country_name": "Worldwide",
            "currency": "USD",
            "exchange_rate": 1.0
        }
    },
    "asset_info": {
        "ATUSD": {
            "asset_name": "US Dollar",
            "decimal_places": 2
        }
    }
}

Response Properties:

payment_id: The unique identifier of the payment.
standard: The SEP standard used for the payment.
country_info: Contains country-specific information.
country_name: The name of the country.
currency: The currency used in the country.
exchange_rate: The exchange rate of the currency compared to the base currency.
asset_info: Contains information about the asset.
asset_name: The name of the asset.
decimal_places: The number of decimal places used for the asset.
Note: Replace the placeholder values (payment ID, country code, and asset code)
in the example request with the actual values to retrieve specific information about a payment.

This API endpoint allows you to retrieve detailed information about a specific payment based on:
the provided payment ID, country code, and asset code.