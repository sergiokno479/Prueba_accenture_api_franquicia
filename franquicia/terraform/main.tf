terraform {
  required_providers {
    digitalocean = {
      source  = "digitalocean/digitalocean"
      version = "~> 2.0"
    }
  }
}

variable "do_token" {}

provider "digitalocean" {
  token = var.do_token
}

# servidor
resource "digitalocean_droplet" "servidor_franquicia" {
  image  = "ubuntu-22-04-x64"
  name   = "api-franquicia-produccion"
  region = "nyc1"
  size   = "s-1vcpu-1gb" # El más económico
}