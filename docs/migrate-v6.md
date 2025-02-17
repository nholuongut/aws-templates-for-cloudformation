<iframe src="https://ghbtns.com/github-btn.html?user=nholuongut&repo=aws-cf-templates&type=star&count=true&size=large" frameborder="0" scrolling="0" width="160px" height="30px"></iframe>

> **New**: Manage Free Templates for AWS CloudFormation with the [nholuongut CLI](./cli/)

# Migrate from v5 to v6

There is one smaller breaking change that affects mostly all templates: The optional parameter `NewRelicLicenseKey` was removed.

[New Relic Servers](https://docs.newrelic.com/docs/servers) and Legacy Alerts are going away on or before May 15, 2018. That's why we removed them from the templates.

If you want to migrate stacks, you can no longer provide the `NewRelicLicenseKey` parameter.
