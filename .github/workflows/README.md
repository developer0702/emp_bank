# Deployments

## GitHub Environment Definitions

To try and make things as straight forward as possible, the following pattern is used:

### Environment Definitions:

We have 1 environment defined for each conceptual environment:

- dev
- test
- stage
- prod

Within the environment definitions, if relevant, we have separated config for the different regions:

- elr
- ctc

Currently, only Stage and Prod are dual-region deployments. All environments must have the `KUBE_CONTEXT_ELR` defined that
contains the generated context file with the PRM and HCC K8S namespace info and credentials. Stage and Prod must have
the additional context defined for `KUBE_CONTEXT_CTC`
